import React, { useState, Component }  from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

// css
import "./style.css";

const [currentIndex, setCurrentIndex] = useState();

function handleChange(index) {
  setCurrentIndex(index);
}

const imageData = [
  {
    label: "Image 1",
    alt: "image1",
    url: "./img/car/car_repair.jpg",
  },
  {
    label: "Image 2",
    alt: "image2",
    url: "./img/car/logo.jpg",
  },
];

const renderSlides = imageData.map((image) => (
  <div key={image.alt}>
    <img src={image.url} alt={image.alt} />
    <p className="legend">{image.label}</p>
  </div>
));

// CLASS Component
export default class Main extends Component {
  // Componentteki yeni isim
  static displayName = "Auto_Main";

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
      <div className="app">
      <Carousel
  showArrows={true}
  autoPlay={true}
  infiniteLoop={true}
  selectedItem={imageData[currentIndex]}
  onChange={handleChange}
  className="carousel-container"
>
  {renderSlides}
</Carousel>
      </div>
        
      </>
    ); //end return
  } // end render
} //end class
