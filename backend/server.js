const express = require('express');
const axios = require('axios');
const cors = require('cors');
const bodyParser = require('body-parser');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(bodyParser.json());

// JDoodle API Endpoint
const JDOODLE_ENDPOINT = "https://api.jdoodle.com/v1/execute";
const JDOODLE_CLIENT_ID = process.env.JDOODLE_CLIENT_ID; // User must set this
const JDOODLE_CLIENT_SECRET = process.env.JDOODLE_CLIENT_SECRET; // User must set this

// Code Execution Endpoint
app.post('/execute', async (req, res) => {
    try {
        const { script, language, versionIndex } = req.body;

        if (!script || !language) {
            return res.status(400).json({ error: "Script and language are required" });
        }

        console.log(`Executing ${language} code...`);

        // Map simplified language names to JDoodle codes
        const languageMap = {
            "kotlin": "kotlin",
            "java": "java",
            "python": "python3",
            "sql": "sql",
            "javascript": "nodejs"
        };
        
        const jdoodleLang = languageMap[language.toLowerCase()] || language;

        const payload = {
            clientId: JDOODLE_CLIENT_ID,
            clientSecret: JDOODLE_CLIENT_SECRET,
            script: script,
            language: jdoodleLang,
            versionIndex: versionIndex || "0"
        };

        const response = await axios.post(JDOODLE_ENDPOINT, payload);

        res.json({
            output: response.data.output,
            statusCode: response.data.statusCode,
            memory: response.data.memory,
            cpuTime: response.data.cpuTime
        });

    } catch (error) {
        console.error("Execution Error:", error.message);
        if (error.response) {
            res.status(error.response.status).json(error.response.data);
        } else {
            res.status(500).json({ error: "Internal Server Error" });
        }
    }
});

app.get('/', (req, res) => {
    res.send('Code Execution Backend is Running');
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
    console.log(`Set JDOODLE_CLIENT_ID and JDOODLE_CLIENT_SECRET in .env file`);
});
