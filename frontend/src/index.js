import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import reportWebVitals from "./reportWebVitals";
import RouterMain from "./components/Home/RouterMain.jsx";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <React.Fragment>
      <RouterMain/>
    </React.Fragment>
  </React.StrictMode>
);

reportWebVitals();
