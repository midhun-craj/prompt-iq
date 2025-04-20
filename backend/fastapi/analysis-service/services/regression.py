def regression_analysis(history: list[str], current: str):
    return {"change": len(current) - len(history[-1]) if history else 0}