import React, { useState} from "react";
import axios from "axios";

interface PromptInputProps {
    setResponse: React.Dispatch<React.SetStateAction<string>>;
    setApiSelected: React.Dispatch<React.SetStateAction<'openai' | 'custom'>>
}

const PromptInput: React.FC<PromptInputProps> = ({ setResponse, setApiSelected }) => {
    const [prompt, setPrompt] = useState<string>('');

    const handleApiChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setApiSelected(e.target.value as 'openai' | 'custom');
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        setResponse('Loading...');

        try {
            const response = await axios.post('/api/${apiSelected}', { prompt });
            setResponse(response.data.result);
        } catch (error) {
            setResponse('Error in fetching response.')
        }
    };

    return (
        <div className="space-y-4">
            <h2 className="text-xl font-bold">
                Input your prompt:
            </h2>

            <select className="p-2 border border-gray-300 rounded-md"
            onChange={handleApiChange}
            >
                <option value="openai">OpenAi</option>
                <option value="custom">Custom API</option>
            </select>

            <textarea
                className="w-full p-2 border border-gray-300 rounded-md"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                placeholder="Type your prompt here..."
                rows={4}
            />

            <button
                className="w-full p-2 bg-blue-500 text-white rounded-md"
                onClick={handleSubmit}
            >
                Submit
            </button>
        </div>
    );
}

export default PromptInput