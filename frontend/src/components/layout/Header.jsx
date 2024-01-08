// rcc
import React, { Component } from 'react'

// css
import './style.css';

// CLASS Component
export default class Header extends Component {
    constructor(props) {
        super(props);
        //bind
        //state
        this.state = {}
        //bind
    }
    // CDM
    componentDidMount() { }
    // FUNCTION
    //RENDER
    render() {
        //RETURN
        return (
            // <div>Header</div>
            //<React.Fragment>Header</React.Fragment>
            <>
                <>
  {/* navbar First start */}
  <nav id="navbar_first">
    <div className="container">
      <div className="row">
        <div id="navbar_first_left" className="col-8">
          <ul>
            <li>
              {" "}
              <button
                className="btn btn-sm btn-primary"
                data-bs-toggle="offcanvas"
                data-bs-target="#offCanvasRegisterId"
              >
                <i className="fa-solid fa-user-secret" />
              </button>
            </li>
            <li>
              {" "}
              <button
                className="btn btn-sm btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#loginModalId"
              >
                <i className="fa-solid fa-circle-user" />
              </button>
            </li>
            <li>
              {" "}
              <button className="btn btn-sm btn-primary" onclick="darkMode()">
                <i className="fa-solid fa-circle-half-stroke" />
              </button>
            </li>
            <li>
              <img src="./img/flag/en.png" className="flag" alt="" />
            </li>
            <li>
              {" "}
              <img src="./img/flag/tr.png" className="flag" alt="" />
            </li>
          </ul>
        </div>
        <div id="navbar_first_right" className="col-4 mx-auto">
          <ul>
            <li>
              {" "}
              <a href="#" target="_blank">
                <i className="fa-brands fa-linkedin text-primary icon_size" />
              </a>{" "}
            </li>
            <li>
              {" "}
              <a href="#" target="_blank">
                <i className="fa-brands fa-youtube text-danger icon_size" />
              </a>{" "}
            </li>
            <li>
              {" "}
              <a href="#" target="_blank">
                <i className="fa-solid fa-blog text-warning icon_size" />
              </a>{" "}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  {/* Modal Login */}
  {/* if you want to close by clicking outside the modal, delete the last endpoint:data-bs-backdrop and data-bs-keyboard */}
  <div
    className="modal fade"
    id="loginModalId"
    tabIndex={-1}
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    role="dialog"
    aria-labelledby="modalTitleId"
    aria-hidden="true"
  >
    <div
      className="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-lg"
      role="document"
    >
      <div className="modal-content">
        <div className="modal-header">
          <h5 className="modal-title" id="modalTitleId">
            Giriş
          </h5>
          <button
            type="button"
            className="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          />
        </div>
        {/* İmportant: password,name,user,username bunları değiştirin */}
        <div className="modal-body">
          <form action="#" method="get" autoComplete="on">
            {/* Email */}
            <div className="form-group mb-3">
              <label htmlFor="email_data">Email</label>
              <input
                type="email"
                id="email_data"
                name="email_data"
                className="form-control"
                placeholder="Emailinizi yazınız..."
                required=""
                autofocus=""
              />
              <label className="text-danger" id="email_data_validation" />
            </div>
            {/* Password */}
            <div className="form-group mb-3">
              <label htmlFor="passwd_data">Şifre</label>
              <input
                type="password"
                id="passwd_data"
                name="passwd_data"
                className="form-control"
                placeholder="Şifrenizi yazınız..."
                required=""
              />
              <label className="text-danger" id="passwd_data_validation" />
            </div>
            <div className="form-group mb-3">
              <button type="reset" className="btn btn-danger">
                Temizle
              </button>
              <button
                type="submit"
                className="btn btn-primary"
                id="loginSubmit"
              >
                Gönder
              </button>
            </div>
          </form>
        </div>
        <div className="modal-footer">
          <button
            type="button"
            className="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Kapat
          </button>
          <button type="button" className="btn btn-primary">
            Save
          </button>
        </div>
      </div>
    </div>
  </div>
  {/* Modal Register: firstName, middleName, lastName, email, passwd, address */}
  <div
    className="offcanvas offcanvas-start bg-dark text-white"
    data-bs-backdrop="static"
    tabIndex={-1}
    id="offCanvasRegisterId"
    aria-labelledby="offCanvasRegisterIdLabel"
  >
    <div className="offcanvas-header">
      <h5 className="offcanvas-title" id="offCanvasRegisterIdLabel">
        Register
      </h5>
      <button
        type="button"
        className="btn-close"
        data-bs-dismiss="offcanvas"
        aria-label="Close"
      />
    </div>
    <div className="offcanvas-body">
      <div>I will not close if you click outside of me.</div>
    </div>
  </div>
  {/* navbar First end */}
  {/* navbar start */}
  <nav
    id="navbar_second"
    className="navbar navbar-expand-md navbar-dark bg-dark fixed-top44"
  >
    <div className="container">
      <a className="navbar-brand" href="#">
        <img src="./img/logo.jpg" alt="it isn't showing logo" id="logo_id" />
      </a>
      <button
        className="navbar-toggler d-lg-none"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#collapsibleNavId"
        aria-controls="collapsibleNavId"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon" />
      </button>
      <div className="collapse navbar-collapse" id="collapsibleNavId">
        <ul className="navbar-nav me-auto mt-2 mt-lg-0">
          <li className="nav-item">
            <a className="nav-link active" href="#" aria-current="page">
              Anasayfa <span className="visually-hidden">(current)</span>
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#store">
              Mağaza
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#order">
              Siparişlerim
            </a>
          </li>                
          <li className="nav-item dropdown">
            <a
              className="nav-link dropdown-toggle"
              href="#"
              id="dropdownId"
              data-bs-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Blog
            </a>
            <div className="dropdown-menu" aria-labelledby="dropdownId">
              <a className="dropdown-item" href="#parts">
                Car Parts
              </a>
              <a className="dropdown-item" href="#repairs">
                Repairs
              </a>
            </div>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#contact">
              İletişim
            </a>
          </li>
        </ul>
        <form className="d-flex my-2 my-lg-0">
          <input
            type="text"
            id="tags"
            className="form-control me-sm-2"
            placeholder="Arama..."
          />
          <button className="btn btn-outline-danger my-2 my-sm-0" type="submit">
            Arama{" "}
          </button>
        </form>
      </div>
    </div>
  </nav>
  {/* navbar end */}
</>

            </>
        ) //end return
    } // end render
}//end class