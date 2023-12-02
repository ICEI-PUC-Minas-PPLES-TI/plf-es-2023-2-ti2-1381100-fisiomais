import React from "react";

export const Filters = ({ totalAppointments, statusOptions, onStatusChange }) => {
  return (
    <div style={{ 
      display: 'flex', 
      justifyContent: 'space-between', 
      alignItems: 'center',
      marginBottom: '1rem'
    }}>
      <p 
      style={{
        marginLeft: '17px'
      }
    }>
        Showing <span className="font-bold">{totalAppointments}</span> Appointments
      </p>
      <select
        onChange={onStatusChange}
        style={{
          display: 'flex',
          alignItems: 'center',
          padding: '8px 16px',
          height: '36px',
          borderRadius: '4px',
          border: '1px solid #DCDFE3',
          fontFamily: "Inter, sans-serif",
          fontSize: '14px',
          marginRight: '16px'
        }}
      >
        <option value="">All Statuses</option>
        {statusOptions.map((status) => (
          <option key={status} value={status}>{status}</option>
        ))}
      </select>
    </div>
  );
};
