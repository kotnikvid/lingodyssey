import { from, of } from "rxjs";
import { catchError, map, switchMap } from "rxjs/operators";
import StreakModel from "../models/Streak.model";
import { StreakType } from "../enums/StreakType.enum";

import logger from "../utils/logger";

export interface StreakDto {
    userEmail: string;
    type: StreakType;
    restartedAt: Date;
    isFailed: boolean;
    createdAt: Date;
}

export class StreakService {
    static getStreakById(id: string) {
        logger.info(`Fetching streak by ID: ${id}`);
        return from(StreakModel.findById(id)).pipe(
            map((streak) => {
                if (!streak) {
                    logger.warn(`Streak with ID: ${id} not found`);
                    return null;
                }
                logger.info(`Streak with ID: ${id} found`);
                return this.mapToDto(streak);
            }),
            catchError((err) => {
                logger.error(`Error fetching streak with ID: ${id}`, err);
                return of(null);
            })
        );
    }

    static getStreaksByUser(userEmail: string) {
        logger.info(`Fetching streaks for user: ${userEmail}`);
        return from(StreakModel.find({ userEmail }).exec()).pipe(
            map((streaks) => {
                logger.info(`Found ${streaks.length} streak(s) for user: ${userEmail}`);
                return streaks.map(this.mapToDto);
            }),
            catchError((err) => {
                logger.error(`Error fetching streaks for user: ${userEmail}`, err);
                return of([]);
            })
        );
    }

    static createOrUpdateStreak(dto: StreakDto) {
        logger.info(`Creating or updating streak for user: ${dto.userEmail}, type: ${dto.type}`);
        return from(StreakModel.findOne({ userEmail: dto.userEmail, type: dto.type })).pipe(
            switchMap((existingStreak) => {
                if (existingStreak) {
                    logger.info(`Updating existing streak for user: ${dto.userEmail}, type: ${dto.type}`);
                    existingStreak.isFailed = false;
                    existingStreak.restartedAt = new Date();
                    return from(existingStreak.save());
                } else {
                    logger.info(`Creating new streak for user: ${dto.userEmail}, type: ${dto.type}`);
                    const streak = new StreakModel(dto);
                    return from(streak.save());
                }
            }),
            catchError((err) => {
                logger.error(`Error creating or updating streak for user: ${dto.userEmail}`, err);
                return of(null);
            })
        );
    }

    static updateStreak(id: string, dto: Partial<StreakDto>) {
        logger.info(`Updating streak with ID: ${id}`);
        return from(StreakModel.findById(id)).pipe(
            switchMap((streak) => {
                if (!streak) {
                    logger.warn(`Streak with ID: ${id} not found`);
                    return of(null);
                }
                streak.restartedAt = dto.restartedAt || new Date();
                streak.isFailed = dto.isFailed ?? streak.isFailed;
                return from(streak.save());
            }),
            catchError((err) => {
                logger.error(`Error updating streak with ID: ${id}`, err);
                return of(null);
            })
        );
    }

    static deleteStreak(id: string) {
        logger.info(`Deleting streak with ID: ${id}`);
        return from(StreakModel.deleteOne({ _id: id })).pipe(
            map((deleteResult) => {
                if (deleteResult.deletedCount) {
                    logger.info(`Streak with ID: ${id} deleted`);
                    return "Deleted";
                } else {
                    logger.warn(`No streak found with ID: ${id}`);
                    return null;
                }
            }),
            catchError((err) => {
                logger.error(`Error deleting streak with ID: ${id}`, err);
                return of(null);
            })
        );
    }

    private static mapToDto(streak: any): StreakDto {
        return {
            userEmail: streak.userEmail,
            type: streak.type,
            restartedAt: streak.restartedAt,
            isFailed: streak.isFailed,
            createdAt: streak.createdAt,
        };
    }
}