import React, { useState } from "react";
import AnalysisSection from "./components/AnalysisSection";
import TimeLineSection from "./components/TimeLineSection";
import PromptInput from "./components/PromptInput";

const App: React.FC = () => {
  const [response, setResponse] = useState<string>('');
  const [apiSelected, setApiSelected] = useState<'openai' | 'custom'>('openai');

  return (
    <div className="flex flex-col min-h-screen w-full bg-gray">
      <header className="w-full p-1 bg-black text-left">
        <h1 className="text-3xl font-light tracking-widest text-white font-bangers pl-4">
          PromptIQ
        </h1>
      </header>
      <div className="flex flex-col md:flex-row flex-1">
        <div className="flex-1 p-6">
          <AnalysisSection response={response}/>
          <TimeLineSection />
        </div>
        <div className="flex-1 p-6 bg-white border-l border-gray-300">
          <PromptInput setResponse={setResponse} setApiSelected={setApiSelected} />
        </div>
      </div>
    </div>
  );
}

export default App