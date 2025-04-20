# import uvicorn
from fastapi import FastAPI
from routes import analysis

app = FastAPI(title="CodeMind Analysis Service")

app.include_router(analysis.router, prefix="/analyze")