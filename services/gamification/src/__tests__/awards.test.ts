import { AwardService } from "../services/awardService";
import AwardModel from "../models/Award.model";
import { of, throwError, firstValueFrom } from "rxjs";

jest.mock("../models/Award.model");

describe("AwardService", () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    test("getAwardById - should return award when found", async () => {
        const mockAward = { _id: "123", name: "Test Award", description: "Test Desc", pointsNeeded: 10 };
        (AwardModel.findById as jest.Mock).mockReturnValue(of(mockAward));

        const result = await firstValueFrom(AwardService.getAwardById("123"));

        expect(result).toEqual(mockAward);
        expect(AwardModel.findById).toHaveBeenCalledWith("123");
    });

    test("getAwardById - should return null when not found", async () => {
        (AwardModel.findById as jest.Mock).mockReturnValue(of(null));

        const result = await firstValueFrom(AwardService.getAwardById("123"));

        expect(result).toBeNull();
    });

    test("getAwardsByUserEmail - should return awards", async () => {
        const mockAwards = [
            { name: "Award1", description: "Desc1", pointsNeeded: 5 },
            { name: "Award2", description: "Desc2", pointsNeeded: 10 },
        ];
        (AwardModel.find as jest.Mock).mockReturnValue({ exec: () => of(mockAwards) });

        const result = await firstValueFrom(AwardService.getAwardsByUserEmail("test@example.com"));

        expect(result).toEqual(mockAwards);
        expect(AwardModel.find).toHaveBeenCalledWith({ userEmails: "test@example.com" });
    });

    test("createAward - should save award", async () => {
        const mockAward = { name: "New Award", description: "Desc", pointsNeeded: 15, save: jest.fn(() => of(mockAward)) };
        (AwardModel as unknown as jest.Mock).mockImplementation(() => mockAward);

        const result = await firstValueFrom(AwardService.createAward(mockAward));

        expect(result).toEqual(mockAward);
        expect(mockAward.save).toHaveBeenCalled();
    });

    test("updateAward - should update and return award", async () => {
        const existingAward = { _id: "123", name: "Old Award", save: jest.fn(() => of({ _id: "123", name: "Updated Award" })) };
        (AwardModel.findById as jest.Mock).mockReturnValue(of(existingAward));

        const result = await firstValueFrom(AwardService.updateAward("123", { name: "Updated Award" }));

        expect(result).toEqual({ _id: "123", name: "Updated Award" });
        expect(existingAward.save).toHaveBeenCalled();
    });

    test("updateAward - should return null if award not found", async () => {
        (AwardModel.findById as jest.Mock).mockReturnValue(of(null));

        const result = await firstValueFrom(AwardService.updateAward("123", { name: "New Name" }));

        expect(result).toBeNull();
    });

    test("deleteAward - should delete and return deleted count", async () => {
        (AwardModel.deleteOne as jest.Mock).mockReturnValue(of({ deletedCount: 1 }));

        const result = await firstValueFrom(AwardService.deleteAward("123"));

        expect(result).toEqual({ deletedCount: 1 });
        expect(AwardModel.deleteOne).toHaveBeenCalledWith({ _id: "123" });
    });

    test("deleteAward - should return 0 deleted count if not found", async () => {
        (AwardModel.deleteOne as jest.Mock).mockReturnValue(of({ deletedCount: 0 }));

        const result = await firstValueFrom(AwardService.deleteAward("nonexistent"));

        expect(result).toEqual({ deletedCount: 0 });
    });

    test("handle errors in getAwardById", async () => {
        (AwardModel.findById as jest.Mock).mockReturnValue(throwError(() => new Error("DB error")));

        await expect(firstValueFrom(AwardService.getAwardById("123"))).rejects.toThrow("DB error");
    });
});
