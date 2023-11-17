import { useState, useEffect } from 'react';
import { v4 as uuidv4 } from 'uuid';
import './Checkbox.scss';

function CustomCheckbox() {
    const [isChecked, setIsChecked] = useState(false);
    const [checkboxId, setCheckboxId] = useState("");

    useEffect(() => {
        setCheckboxId(uuidv4());
    }, []);

    return (
        <div className="checkbox-container">
            <input 
                type="checkbox" 
                id={checkboxId} 
                className="hidden-checkbox"
                checked={isChecked} 
                onChange={() => setIsChecked(!isChecked)} 
            />
            <label htmlFor={checkboxId}>
                {isChecked ? <CheckedSVG /> : <UncheckedSVG />}
            </label>
        </div>
    );
}

const UncheckedSVG = () => (
    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" fill="none">
        <rect x="0.75" y="0.75" width="18.5" height="18.5" rx="2.25" stroke="#A5ADD7" strokeWidth="1.5"/>
    </svg>
);

const CheckedSVG = () => (
    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" fill="none">
        <rect width="20" height="20" rx="3" fill="#6774BD"/>
        <path d="M4.29306 10.7212C3.39997 9.68471 4.75038 8.09982 5.64331 9.13634L8.3843 12.3037L14.3502 5.34088C15.2433 4.29852 16.601 5.88322 15.7079 6.92558L9.06654 14.6727C8.69361 15.1081 8.08874 15.1094 7.71441 14.6749L4.29306 10.7212Z" fill="white"/>
    </svg>
);

export default CustomCheckbox;
