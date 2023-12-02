import React, {useState} from "react";
import { PrimaryButton } from "./PrimaryButton";
import { IconChevronDown1 } from "../icons/IconChevronDown1";
import { IconChevronLeft } from "../icons/IconChevronLeft";
import { IconChevronRight } from "../icons/IconChevronRIght";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css"

export const HeaderTable = () => {

  const [currentDate, setCurrentDate] = useState(new Date());
  const [calendarVisible, setCalendarVisible] = useState(false);

  const nextDay = () => {
    setCurrentDate(new Date(currentDate.setDate(currentDate.getDate() + 1)));
  };

  const prevDay = () => {
    setCurrentDate(new Date(currentDate.setDate(currentDate.getDate() - 1)));
  };

  const formateDate = (date) => {
    return date.toLocaleDateString("en-US", { year: "numeric", month: "short", day: "numeric" });
  };

  const visibleCalendar = () => {
    setCalendarVisible(!calendarVisible);
  };

  const handleCalendarChange = (date) => {
    setCurrentDate(date);
    visibleCalendar();
  };

  return (
    <div className="relative w-full h-[44px]">
      <div className="flex justify-between items-center px-4">
        <div className="text-[32px] font-semibold [font-family:'Inter',Helvetica]">
          Consultas
        </div>
        <div className="flex items-center gap-[24px]">
          <div className="flex items-center gap-[4px] p-[8px] rounded-[36px] border-[0.7px] border-solid border-gray-400 cursor-pointer" onClick={prevDay}>
            <IconChevronLeft className="w-[20px] h-[20px]"/>
          </div>
          <div className="flex items-center gap-[4px]">
            <div className="text-gray-900 font-semibold [font-family:'Inter',Helvetica] text-[14px]">
              {formateDate(currentDate)}
            </div>
            <div className="text-[#585e6a] font-normal [font-family:'Inter',Helvetica] text-[14px] cursor-pointer" onClick={visibleCalendar}>
              Today
            </div>
            <IconChevronDown1 className="w-[20px] h-[20px]"/>
            {calendarVisible && (
              <DatePicker
                selected={currentDate}
                onChange={handleCalendarChange}
                style={"z-index: 1000"}
                />
            )}
            <div className="flex items-center gap-[4px] p-[8px] rounded-[36px] border-[0.7px] border-solid border-gray-400 cursor-pointer" onClick={nextDay}>
              <IconChevronRight className="w-[20px] h-[20px]"/>
            </div>
          </div>
          <PrimaryButton stateProp="default" />
        </div>
      </div>
    </div>
  );
};
