import React from "react";

export const IconSort = ({ color = "#666E7D", stroke = "#666E7D", className }) => {
  return (
    <svg
      className={`${className}`}
      fill="none"
      height="20"
      viewBox="0 0 20 20"
      width="20"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M4.26777 8.19039L6.62488 5.83328L8.982 8.19039"
        stroke={color}
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="1.2"
      />
      <path
        d="M6.62493 5.8333L6.62493 14.0832"
        stroke={color}
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="1.2"
      />
      <path
        d="M15.5653 12.5594L13.2082 14.9165L10.8511 12.5594"
        stroke={stroke}
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="1.2"
      />
      <path
        d="M13.2082 6.66667L13.2082 14.9166"
        stroke={stroke}
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="1.2"
      />
    </svg>
  );
};