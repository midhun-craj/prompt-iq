from sentence_transformers import SentenceTransformer, util

model = SentenceTransformer("all-MiniLM-L6-V2")

def similarity_score(text1: str, text2: str) -> float:
    emb1 = model.encode(text1, convert_to_tensor=True)
    emb2 = model.encode(text2, convert_to_tensor=True)
    return float(util.pytorch_cos_sim(emb1, emb2)[0][0])