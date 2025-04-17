import express from 'express';
import axios from 'axios';

const router = express.Router();

interface FlashcardDto {
    id: string;
    cardSetId: string;
    title: string;
    body: string;
    correctAnswer: string;
    pointsAwarded: number;
    otherAnswers: string[];
}

interface CardSetDto {
    id: string;
    name: string;
    userEmail: string;
    languageName: string;
    languageCode: string;
    flashCards: FlashcardDto[];
}

const API_URL = process.env.API_URL || 'http://localhost:8081';

const cardService = {
    getCardSetById: async (id: string) => {
        const res = await axios.get(`${API_URL}/cardSets/${id}`);
        return res.data;
    },

    getCardSets: async () => {
        const res = await axios.get(`${API_URL}/cardSets`);
        return res.data;
    },

    getCardSetsByLanguage: async (languageName?: string, languageCode?: string) => {
        const params: any = {};
        if (languageName) params.languageName = languageName;
        if (languageCode) params.languageCode = languageCode;

        const res = await axios.get(`${API_URL}/cardSets/language`, { params });
        return res.data;
    },

    // Flashcards
    getFlashcardById: async (id: string) => {
        const res = await axios.get(`${API_URL}/flashCards/${id}`);
        return res.data;
    },
};

router.get('/cardSets', async (req, res) => {
    const result = await cardService.getCardSets();
    res.json(result);
});

router.get('/cardSets/language', async (req, res) => {
    const { languageName, languageCode } = req.query;
    const result = await cardService.getCardSetsByLanguage(languageName as string, languageCode as string);
    console.log(result);
    res.json(result);
});

router.get('/cardSets/:id', async (req, res) => {
    const result = await cardService.getCardSetById(req.params.id);
    res.json(result);
});



export default router;