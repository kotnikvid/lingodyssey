import express from 'express';
import { authenticateJWT } from '../middleware/authMiddleware';
import { AwardService } from '../services/awardService';
import { of } from 'rxjs';
import { switchMap, catchError } from 'rxjs/operators';
import logger from "../utils/logger";

const router = express.Router();

interface AwardDto {
    name: string;
    description: string;
    pointsNeeded: number;
    createdBy: string;
}

interface AwardUserDto {
    points: number;
    email: string;
}

class AwardResponseDto {
    name: string;
    description: string;
    pointsNeeded: number;
}

class UserDto {
    id: string;
    email: string;
    displayName?: string;
    fullName?: string;
    birthDate?: Date;
    points: number = 0;
}

// Get Award by ID
router.get('/:id', authenticateJWT(['User', 'Admin']), (req, res) => {
    AwardService.getAwardById(req.params.id).pipe(
        switchMap((award) => {
            if (!award) {
                return of(res.status(404).send('Not Found'));
            }

            const responseDto = new AwardResponseDto();
            responseDto.name = award.name;
            responseDto.description = award.description;
            responseDto.pointsNeeded = award.pointsNeeded;

            return of(res.json(responseDto));
        }),
        catchError((error) => {
            logger.error(error);
            return of(res.status(500).send('Internal server error'));
        })
    ).subscribe();
});

// Create Award
router.post('/', authenticateJWT(['Admin']), (req, res) => {
    const dto: AwardDto = req.body;
    dto.createdBy = req.user.sub.toString();

    AwardService.createAward(dto).pipe(
        switchMap((award) => of(res.json(award))),
        catchError((error) => {
            logger.error(error);
            return of(res.status(500).send('Internal server error'));
        })
    ).subscribe();
});

// Update Award
router.put('/:id', authenticateJWT(['Admin']), (req, res) => {
    const dto: AwardDto = req.body;
    const id = req.params.id;

    AwardService.updateAward(id, dto).pipe(
        switchMap((updatedAward) => {
            if (!updatedAward) {
                return of(res.status(404).send('Award not found or could not be updated'));
            }
            return of(res.json(updatedAward));
        }),
        catchError((error) => {
            logger.error(error);
            return of(res.status(500).send('Internal server error'));
        })
    ).subscribe();
});

// Delete Award
router.delete('/:id', authenticateJWT(['Admin']), (req, res) => {
    const id = req.params.id;

    AwardService.deleteAward(id).pipe(
        switchMap((result) => {
            if (result.deletedCount === 0) {
                return of(res.status(404).send('Award not found'));
            }
            return of(res.status(204).send('Deleted'));
        }),
        catchError((error) => {
            logger.error(error);
            return of(res.status(500).send('Internal server error'));
        })
    ).subscribe();
});

// Assign Award to User
router.post('/awardUser', authenticateJWT(['Admin', 'User']), (req: express.Request<{}, {}, AwardUserDto>, res: express.Response) => {
    const { points, email } = req.body;

    if (!points || !email) {
        res.status(400).send('Points and email must be provided');
        return;
    }

    AwardService.sendMessageToRabbitMQ(points, email).subscribe({
        next: (v) => {
            res.status(200).send('Message sent to RabbitMQ successfully');
        },
        error: (err: Error) => {
            logger.error(err);
            res.status(500).send('Error sending message to RabbitMQ - ');
        },
    });
});

//Get Awards by user
router.get('/userAwards/:userEmail', authenticateJWT(['Admin', 'User']), (req, res) => {
    const user = req.params.userEmail;

    if (!user) {
        res.status(400).send('User must be provided');
        return;
    }

    AwardService.getAwardsByUserEmail(user).pipe(
        switchMap((result) => {
            if (!result) {
                return of(res.status(404).send('Award not found'));
            }
            return of(res.status(200).json(result));
        }),
        catchError((error) => {
            logger.error(error);
            return of(res.status(500).send('Internal server error'));
        })
    ).subscribe();
});

export default router;