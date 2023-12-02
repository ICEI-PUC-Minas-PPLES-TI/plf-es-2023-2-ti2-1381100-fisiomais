import PropTypes from "prop-types";
import React, { useReducer } from "react";
import { useNavigate } from "react-router-dom";
import { IconPlus } from "../icons/IconPlus";

export const PrimaryButton = ({ stateProp, className }) => {
  const [state, dispatch] = useReducer(reducer, {
    state: stateProp || "default",
  });
  const navigate = useNavigate();

  const handleClick = () => {
    dispatch("click");
    navigate('/nova-consulta'); 
  };

  return (
    <button
      type="button"
      className={`inline-flex items-center justify-center gap-2 px-6 py-3 rounded-md relative ${
        state.state === "default" ? "bg-accent" : "bg-accent-dark"
      } ${className}`}
      onMouseLeave={() => {
        dispatch("mouse_leave");
      }}
      onMouseEnter={() => {
        dispatch("mouse_enter");
      }}
      onClick={handleClick}
    >
      <IconPlus className="w-5 h-5" color={state.state === "pressed" ? "#FFCD60" : "white"} />
      <span
        className={`font-inter font-bold tracking-wide text-sm leading-5 ${
          state.state === "pressed" ? "text-textaccent" : "text-white"
        }`}
      >
        New Appointment
      </span>
    </button>
  );
};

function reducer(state, action) {
  switch (action) {
    case "mouse_enter":
      return {
        state: "hover",
      };
    case "mouse_leave":
      return {
        state: "default",
      };
    case "click":
      return {
        state: "pressed",
      };
    default:
      return state;
  }
}

PrimaryButton.propTypes = {
  stateProp: PropTypes.oneOf(["default", "pressed", "hover"]),
  className: PropTypes.string,
};
