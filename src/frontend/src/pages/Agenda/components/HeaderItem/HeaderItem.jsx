/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React from "react";
import { useReducer } from "react";
import { IconSort } from "../../icons/IconSort/IconSort";

export const HeaderItem = ({ displaying, className, text = "TIME", iconSortColor = "#666E7D" }) => {
  const [state, dispatch] = useReducer(reducer, {
    displaying: displaying || "default",
  });

  return (
    <div
      className={`w-[116px] h-[48px] ${className}`}
      onMouseLeave={() => {
        dispatch("mouse_leave");
      }}
      onMouseEnter={() => {
        dispatch("mouse_enter");
      }}
    >
      <div className="inline-flex items-center top-[14px] gap-[8px] relative">
        <div className="[font-family:'Inter',Helvetica] w-fit mt-[-1.00px] tracking-[0] text-[12px] text-primary font-semibold leading-[20px] whitespace-nowrap relative">
          {text}
        </div>
        {state.displaying === "over" && (
          <IconSort className="!relative !w-[20px] !h-[20px]" color={iconSortColor} stroke="#666E7D" />
        )}
      </div>
    </div>
  );
};

function reducer(state, action) {
  switch (action) {
    case "mouse_enter":
      return {
        ...state,
        displaying: "over",
      };

    case "mouse_leave":
      return {
        ...state,
        displaying: "default",
      };
  }

  return state;
}
