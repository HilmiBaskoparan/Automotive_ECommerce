// rcc
import React, { Component } from "react";
import Header from "./Header";
import Main from "./Main";
import Footer from "./Footer";

// CLASS Component
export default class RouterMain extends Component {
  constructor(props) {
    super(props);

    //bind

    //state
    this.state = {};

    //bind
  }

  // CDM
  componentDidMount() {}

  // FUNCTION

  //RENDER
  render() {
    //RETURN
    return (
      // <div>Header</div>
      //<React.Fragment>Header</React.Fragment>
      <>
        <Header />
        <br />
        <Main />
        <br />
        <Footer />
      </>
    ); //end return
  } // end render
} //end class
