

// Images
import banner1 from "../../img/banner/banner-1.jpg";
import banner2 from "../../img/banner/banner-2.jpg";

const HomePageBanner = () => {
  return (
    <div className="banner spad">
      <div className="container">
        <div className="row">
          <div className="col-lg-7 offset-lg-4">
            <div className="banner__item">
              <div className="banner__item__pic">
                <img
                  src={banner1}
                  width={457.5}
                  height={457.5}
                  alt="banner1"
                />
              </div>
              <div className="banner__item__text">
                <a href="/products">Shop now</a>
              </div>
            </div>
          </div>
          <div className="col-lg-5">
            <div className="banner__item banner__item--middle">
              <div className="banner__item__pic">
                <img
                  src={banner2}
                  width={457.5}
                  height={457.5}
                  alt="banner2"
                />
              </div>
              <div className="banner__item__text">
                <a href="/products">Shop now</a>
              </div>
            </div>
          </div>
          <div className="col-lg-7">
            <div className="banner__item banner__item--last">
              <div className="banner__item__pic">
                <img
                  src={banner1}
                  width={457.5}
                  height={457.5}
                  alt="banner3"
                />
              </div>
              <div className="banner__item__text">
                <a href="/products">Shop now</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePageBanner;