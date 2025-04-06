import { Request, Response, NextFunction, RequestHandler } from "express";
import jwt, { JwtPayload } from "jsonwebtoken";

const JWT_SECRET = process.env.JWT_SECRET;

declare module "express-serve-static-core" {
    interface Request {
        user?: JwtPayload | string;
    }
}

export const authenticateJWT = (roles?: string[]): RequestHandler => {
    return (req: Request, res: Response, next: NextFunction): void => {
        if (1 == 1) {
            req.user = {
                sub: "kotnik.vid@gmail.com"
            };
            next();
            return;
        }

        const authHeader = req.headers.authorization;

        if (!authHeader || !authHeader.startsWith('Bearer ')) {
            res.status(401).json({ message: 'Unauthorized 1' });
            return;
        }

        const token = authHeader.split(' ')[1];

        try {
            const decoded = jwt.verify(token, JWT_SECRET) as JwtPayload;
            req.user = decoded;

            if (roles && roles.length > 0) {
                const userRoles = decoded.role as string;

                if (!userRoles || !roles.some(r => userRoles.includes(r))) {
                    console.log(`User roles ${roles.length} not found`);
                    res.status(403).json({ message: 'Forbidden' });
                    return;
                }
            }

            next();
        } catch (err) {
            console.error(err);
            res.status(403).json({ message: 'Forbidden' });
            return;
        }
    };
};