import React from "react"

interface AnalysisSectionProps {
    response: string
}

const AnalysisSection: React.FC<AnalysisSectionProps> = ({ response }) => {
    return (
        <div className="mb-6">
            <h2 className="text-xl font-bold mb-2">
                Analysis
            </h2>
            <div className="p-4 bg-white border border-gray-300 shadow-md rounded-md">
                <p>{response || 'waiting for prompt...'}</p>
            </div>
        </div>
    );
}

export default AnalysisSection;