import mongoose, {Schema, Document, Model} from "mongoose";
import {StreakType} from "../enums/StreakType.enum";

export interface IStreak extends Document {
    userEmail: string;
    type: StreakType;
    restartedAt: Date;
    isFailed: boolean;
    createdAt: Date;
    updatedAt: Date;
}

const streakSchema: Schema<IStreak> = new Schema<IStreak>({
    userEmail: {type: String, required: true},
    type: {
        type: String,
        enum: Object.values(StreakType), // Validate against enum values
        required: true
    },
    restartedAt: {type: Date, default: Date.now },
    isFailed: {type: Boolean, default: false },
}, {
    timestamps: true
});

streakSchema.index({userEmail: 1, type: 1}, {unique: true});

const StreakModel: Model<IStreak> = mongoose.model<IStreak>("Streak", streakSchema);

export default StreakModel;