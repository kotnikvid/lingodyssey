import mongoose, { Schema, Document, Model } from "mongoose";

export interface IAward extends Document {
    name: string;
    description: string;
    pointsNeeded: number;
    userEmails: string[];
    createdBy: string;
    createdAt: Date;
    updatedAt: Date;
}

const awardSchema: Schema<IAward> = new Schema<IAward>(
    {
        name: { type: String, required: true, unique: true },
        description: { type: String, required: true },
        pointsNeeded: { type: Number, required: true },
        userEmails: { type: [String], required: true },
        createdBy: { type: String, required: true }
    },
    { timestamps: true }
)

const AwardModel: Model<IAward> = mongoose.model<IAward>("Award", awardSchema);

export default AwardModel;