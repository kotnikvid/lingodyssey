import { StreakService, StreakDto } from "../services/streakService";
import StreakModel from "../models/Streak.model";
import { of, throwError } from "rxjs";
import { StreakType } from "../enums/StreakType.enum";

jest.mock("..//models/Streak.model");

describe("StreakService", () => {
    const mockStreak: StreakDto = {
        userEmail: "test@example.com",
        type: StreakType.DAILY,
        restartedAt: new Date(),
        isFailed: false,
        createdAt: new Date(),
    };

    afterEach(() => {
        jest.clearAllMocks();
    });

    test("getStreakById should return a streak when found", (done) => {
        (StreakModel.findById as jest.Mock).mockReturnValue(of(mockStreak));

        StreakService.getStreakById("123").subscribe({
            next: (streak) => {
                expect(streak).toEqual(mockStreak);
                done();
            },
        });
    });

    test("getStreakById should return null when streak is not found", (done) => {
        (StreakModel.findById as jest.Mock).mockReturnValue(of(null));

        StreakService.getStreakById("123").subscribe({
            next: (streak) => {
                expect(streak).toBeNull();
                done();
            },
        });
    });

    test("createOrUpdateStreak should create a new streak if none exists", (done) => {
        (StreakModel.findOne as jest.Mock).mockReturnValue(of(null));
        (StreakModel.prototype.save as jest.Mock).mockReturnValue(of(mockStreak));

        StreakService.createOrUpdateStreak(mockStreak).subscribe({
            next: (streak) => {
                expect(streak).toEqual(mockStreak);
                done();
            },
        });
    });

    test("createOrUpdateStreak should update an existing streak", (done) => {
        const existingStreak = { ...mockStreak, save: jest.fn().mockReturnValue(of(mockStreak)) };
        (StreakModel.findOne as jest.Mock).mockReturnValue(of(existingStreak));

        StreakService.createOrUpdateStreak(mockStreak).subscribe({
            next: (streak) => {
                expect(streak).toEqual(mockStreak);
                expect(existingStreak.save).toHaveBeenCalled();
                done();
            },
        });
    });

    test("deleteStreak should return 'Deleted' if deletion is successful", (done) => {
        (StreakModel.deleteOne as jest.Mock).mockReturnValue(of({ deletedCount: 1 }));

        StreakService.deleteStreak("123").subscribe({
            next: (message) => {
                expect(message).toBe("Deleted");
                done();
            },
        });
    });

    test("deleteStreak should return null if no streak is deleted", (done) => {
        (StreakModel.deleteOne as jest.Mock).mockReturnValue(of({ deletedCount: 0 }));

        StreakService.deleteStreak("123").subscribe({
            next: (message) => {
                expect(message).toBeNull();
                done();
            },
        });
    });
});
