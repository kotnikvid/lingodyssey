import express from "express";
import { authenticateJWT } from "../middleware/authMiddleware";
import { StreakService, StreakDto } from "../services/streakService";

const router = express.Router();

// Get Streak by ID
router.get("/:id", authenticateJWT(["User", "Admin"]), (req: express.Request, res: express.Response) => {
    const { id } = req.params;

    StreakService.getStreakById(id).subscribe({
        next: (streak) => {
            if (streak) {
                res.json(streak);
            } else {
                res.status(404).send("Not Found");
            }
        },
        error: (err) => {
            console.error(err);
            res.status(500).send("Internal server error");
        },
    });
});

// Create or Update Streak
router.post("/", authenticateJWT(["User", "Admin"]), (req: express.Request<{}, {}, StreakDto>, res: express.Response) => {
    const dto = req.body;

    StreakService.createOrUpdateStreak(dto).subscribe({
        next: (streak) => {
            if (streak) {
                res.json(streak);
            } else {
                res.status(500).send("Error processing streak");
            }
        },
        error: (err) => {
            console.error(err);
            res.status(500).send("Internal server error");
        },
    });
});

// Update Streak
router.put("/:id", authenticateJWT(["User", "Admin"]), (req: express.Request<{ id: string }, {}, StreakDto>, res: express.Response) => {
    const { id } = req.params;
    const dto = req.body;

    StreakService.updateStreak(id, dto).subscribe({
        next: (streak) => {
            if (streak) {
                res.json(streak);
            } else {
                res.status(404).send("Streak not found");
            }
        },
        error: (err) => {
            console.error(err);
            res.status(500).send("Internal server error");
        },
    });
});

// Delete Streak
router.delete("/:id", authenticateJWT(["Admin"]), (req: express.Request<{ id: string }, {}, {}>, res: express.Response) => {
    const { id } = req.params;

    StreakService.deleteStreak(id).subscribe({
        next: (result) => {
            if (result) {
                res.status(204).send(result);
            } else {
                res.status(404).send("Not Found");
            }
        },
        error: (err) => {
            console.error(err);
            res.status(500).send("Internal server error");
        },
    });
});

export default router;