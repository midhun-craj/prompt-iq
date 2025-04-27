import React from "react";

const TimeLineSection: React.FC = () => {
    return (
        <div>
            <h2 className="text-xl font-bold mb-2">
                Timeline
            </h2>
            <div className="p-4 bg-white border border-gray-300 shadow-md rounded-md">
                <ul className="space-y-2">
                    <li>Step 1: Prompt Received</li>
                    <li>Step 1: API Requested</li>
                    <li>Step 1: Response Analyzed</li>
                </ul>
            </div>
        </div>
    );
};

export default TimeLineSection;