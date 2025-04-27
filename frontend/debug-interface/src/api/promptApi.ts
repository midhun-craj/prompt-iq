import axios from "axios";

const getResponseFromAPI = async (apiType: 'openai' | 'custom', prompt: string) => {
    try {
        const response = await axios.post('/api/${apiType}', { prompt });
        return response.data.result;
    } catch (error) {
        throw new Error('Error fetching response');
    }
};

export default getResponseFromAPI;