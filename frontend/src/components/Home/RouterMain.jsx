// rcc
import React, { Component } from "react";
import Header from "../layout/Header";
import HomePage from "./HomePage";
import HomePageBanner from "./HomePageBanner";
import Footer from "../layout/Footer";

// CLASS Component
export default class RouterMain extends Component{
  constructor(props) {
    super(props);
    this.state = {};

  }

  componentDidMount() {}

  //RENDER
  render() {
    //RETURN
    return (
      // <div>Header</div>
      //<React.Fragment>Header</React.Fragment>
      <>
        <Header />
        <br />
        <HomePage />
        <HomePageBanner></HomePageBanner>
        <br />
        <Footer />
      </>
    ); //end return
  } // end render
} //end class
