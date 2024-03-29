import React from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

// CSS
import "../css/style.css";

// Images
import logo from "../../img/car/logo.jpg";
import repair from "../../img/car/car_repair.jpg";


const HomePage = () => {
  return (
    <div>
      <section className="hero">
        <div className="hero__slider">
          <div
            id="carouselExampleIndicators"
            className="carousel slide"
            data-ride="carousel"
          >
            <ol className="carousel-indicators">
              <li
                data-target="#carouselExampleIndicators"
                data-slide-to={0}
                className="active"
              />
              <li data-target="#carouselExampleIndicators" data-slide-to={1} />
            </ol>

            <div className="carousel-inner">
              {/* <div className="text-carousel">
                <div className="">
                  <h6>Summer Collection</h6>
                  <h2>Fall - Winter Collections 2030</h2>
                  <p>
                    A specialist label creating luxury essentials. Ethically
                  </p>
                  <a href="#" className="primary-btn">
                    Shop now <span className="arrow_right" />
                  </a>
                  <div className="hero__social">
                    <a href="#">
                      <i className="fa fa-facebook" />
                    </a>
                    <a href="#">
                      <i className="fa fa-twitter" />
                    </a>
                    <a href="#">
                      <i className="fa fa-pinterest" />
                    </a>
                    <a href="#">
                      <i className="fa fa-instagram" />
                    </a>
                  </div>
                </div>
              </div> */}
              <Carousel
                autoPlay={true}
                showThumbs={false}
                showArrows={true}
                interval={5000}
                infiniteLoop={true}
              >
                <div className="carousel-item active">
                  <img src={logo} alt="Fotoğraf Hatalı" className="img_car"/>
                </div>
                <div className="carousel-item active">
                  <img src={repair} alt="Fotoğraf Hatalı" className="img_car"/>
                </div>
               
              </Carousel>
            </div>
            {/* <a
              className="carousel-control-prev"
              href="#carouselExampleIndicators"
              role="button"
              data-slide="prev"
            >
              <span className="carousel-control-prev-icon" aria-hidden="true" />
              <span className="sr-only">Previous</span>
            </a>

            <a
              className="carousel-control-next bg-red"
              href="#carouselExampleIndicators"
              role="button"
              data-slide="next"
            >
              <span className="carousel-control-next-icon" aria-hidden="true" />
              <span className="sr-only">Next</span>
            </a> */}
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;