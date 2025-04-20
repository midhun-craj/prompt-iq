import difflib

def get_diff(text1: str, text2: str):
    diff = difflib.unified_diff(
        text1.splitlines(),
        text2.splitlines(),
        lineterm=""
    )
    return "\n".join(diff)