from fastapi import APIRouter
from pydantic import BaseModel
from services import diffing, embeddings, regression

router = APIRouter()

class AnalysisRequest(BaseModel):
    old_prompt: str
    new_prompt: str
    old_response: str
    new_response: str
    history: list[str] = []

@router.post("/")
def analyze(data: AnalysisRequest):
    diff_result = diffing.get_diff(data.old_response, data.new_response)
    similarity = embeddings.similarity_score(data.old_response, data.new_response)
    reg = regression.regression_analysis(data.history, data.new_prompt)

    return {
        "diff": diff_result, 
        "similarity": similarity,
        "regression": reg
    }

@router.get("/health")
def health_check():
    return {"status": "ok"}