import { Content } from "antd/es/layout/layout";
import React, { useState } from "react";
import "./styles/agenda.scss"
import "./styles/global.css"
import CustomCheckbox from "./Components/Checkbox/Checkbox";
import SearchComponent from "./Components/SearchFields/Search";

const Agenda = () => {
  const [hideVisited, setHideVisited] = useState(false);
  const [showEmpty, setShowEmpty] = useState(false);
  const [checkboxNeutral, setCheckboxNeutral] = useState(false);
  const [checkboxAppointment, setCheckboxAppointment] = useState(false);

  return (
    <Content>
      <div className="appointmentsManage">
        <div className="content">
          <div className="filters">
            <div className="showing">
              <div className="text2">
                <span className="text-txt">
                  <span>
                    <span>Mostrando</span>
                    <span className="span">{` `}</span>
                  </span>
                  <b>1 consulta</b>
                </span>
              </div>
            </div>
            <div className="filters1">
              <div className="hide-visited">
                <div className="text3">Hide visited</div>
                <div className="checkboxdefault">
                  <CustomCheckbox
                    checked={hideVisited}
                    onChange={() => setHideVisited(!hideVisited)}
                  />
                </div>
              </div>
              <div className="show-empty">
                <div className="text4">Show Empty</div>
                <div className="checkboxdefault">
                  <CustomCheckbox
                    checked={showEmpty}
                    onChange={() => setShowEmpty(!showEmpty)}
                  />
                </div>
              </div>
            </div>
          </div>
          <div className="table">
            <div className="table-top">
              <div className="line">
                <div className="headers">
                  <div className="status">
                    <div className="item">
                      <div className="patient">Status</div>
                    </div>
                  </div>
                  <div className="creator">
                    <div className="item">
                      <div className="patient">Contato</div>
                    </div>
                  </div>
                  <div className="technician">
                    <div className="item">
                      <div className="patient">Medico</div>
                    </div>
                  </div>
                  <div className="location">
                    <div className="item">
                      <div className="patient">Localização</div>
                    </div>
                  </div>
                  <div className="test">
                    <div className="item">
                      <div className="patient">Exercicio</div>
                    </div>
                  </div>
                  <div className="insurance">
                    <div className="item">
                      <div className="patient">Plano Medico</div>
                    </div>
                  </div>
                  <div className="patient1">
                    <div className="item">
                      <div className="patient">Paciente</div>
                    </div>
                  </div>
                  <div className="time">
                    <div className="item7">
                      <div className="patient">Horario</div>
                      <img className="iconsort" alt="" src="/iconsort.svg" />
                    </div>
                  </div>
                </div>
                <div className="checkbox">
                  <div className="checkboxneutral1">
                    <CustomCheckbox
                      checked={checkboxNeutral}
                      onChange={() => setCheckboxNeutral(!checkboxNeutral)}
                    />
                  </div>
                </div>
              </div>
            </div>
            <div className="table-line">
              <div className="content-line">
                <div className="users-appointment">
                  <div className="content-icon">
                    <div className="content2">
                      <div className="patient3">Guilherme Henrique</div>
                      <div className="insurance1">
                        <div className="insurance2">Unimed</div>
                      </div>
                      <div className="test2">
                        Fortalecimento
                      </div>
                      <div className="patient3">José</div>
                      <div className="location2">
                        Google Meet
                      </div>
                      <img className="creator-icon" alt="" src="/creator.svg" />
                      <div className="status2">
                        <div className="visited">
                          <p className="waiting">Waiting</p>
                          <p className="p">
                            <b>
                              <span>05:54</span>
                              <span className="span1">{` `}</span>
                            </b>
                          </p>
                        </div>
                        <img
                          className="iconwaiting"
                          alt=""
                          src="/iconwaiting.svg"
                        />
                      </div>
                    </div>
                  </div>
                  <div className="time2">18:00 - 18:45</div>
                  <div className="icon1">
                    <CustomCheckbox
                      checked={checkboxAppointment}
                      onChange={() =>
                        setCheckboxAppointment(!checkboxAppointment)
                      }
                    />
                  </div>
                </div>
              </div>
              <img className="more-icon" alt="" src="/more.svg" />
            </div>
            <div className="table-line8">
              <div className="content-line">
                <div className="users-appointment">
                  <img className="content-icon" alt="" src="/content.svg" />
                  <div className="time2">07:30 - 08:30</div>
                  <div className="icon1">
                    <CustomCheckbox
                      checked={checkboxAppointment}
                      onChange={() =>
                        setCheckboxAppointment(!checkboxAppointment)
                      }
                    />
                  </div>
                </div>
              </div>
              <img className="more-icon" alt="" src="/more1.svg" />
            </div>

            <div className="icons">
              <img className="icon-del" alt="" src="/iconadd1.svg" />
              <img className="icon-edit" alt="" src="/iconadd1.svg" />
              <img className="icon-reschedule" alt="" src="/iconadd1.svg" />
              <img className="arrow-icon" alt="" src="/iconadd1.svg" />
            </div>
          </div>
        </div>
        <div className="header-table">
          <div className="right">
            <div className="date-controls">
              <div className="button">
                <img
                  className="icondropdown"
                  alt=""
                  src="/iconchevronleft.svg"
                />
              </div>
              <div className="date">
                <div className="jun-24-2022">Jun 24, 2022</div>
                <div className="today">Today</div>
                <img className="icondropdown" alt="" src="/icondropdown.svg" />
              </div>
              <div className="button1">
                <img
                  className="icondropdown"
                  alt=""
                  src="/iconchevronright.svg"
                />
              </div>
            </div>
            <div className="new-appointment">
              <img className="iconsort" alt="" src="/icon-plus.svg" />
              <b className="text6">Nova Consulta</b>
            </div>
          </div>
          <div className="appointments">Consultas</div>
        </div>
        <div className="divider" />
        <div className="top">
          <SearchComponent />
        </div>
      </div>
    </Content>
  );
};

export default Agenda;
