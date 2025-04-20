# Build the application
FROM python:3.9-slim-bullseye AS builder

WORKDIR /app

COPY requirements.txt .

RUN --mount=type=cache,target=/root/.cache \
    pip install --upgrade pip && \
    pip install --no-cache-dir --prefer-binary --prefix=/install -r requirements.txt

# RUN pip install huggingface_hub[hf_xet]

COPY . .

# Production image
FROM python:3.9-slim-bullseye

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

RUN useradd -m appuser
# RUN adduser --disabled-password --no-create-home appuser

WORKDIR /app

COPY --from=builder --chown=appuser:appuser /install /usr/local
COPY --from=builder --chown=appuser:appuser /app /app

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1

RUN chown -R appuser:appuser /app /usr/local
    
USER appuser

CMD [ "uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000" ]