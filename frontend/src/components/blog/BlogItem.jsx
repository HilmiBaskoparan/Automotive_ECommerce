import cn from "classnames";
import styles from "./styles.module.css";

const BlogItem = ({ blog, className }) => {
  return (
    // <p className={cn(styles["my-text"], styles["text-color"])}>sdsadsad</p>

    <div className={className}>
      <div className="blog__item">
        <div className="blog__item__pic set-bg">
          {" "}
          <img src={blog.img} width="360" height="270" alt="blog" />
        </div>
        <div className={cn("blog__item__text", styles["blog_item"])}>
          <span>
            <img
              src="/images/icon/calendar.png"
              width="13"
              height="13"
              alt="calendat"
            />{" "}
            16 February 2020
          </span>
          <h5>What Curling Irons Are The Best Ones</h5>
          <a href="/blogs/1">Read More</a>
        </div>
      </div>
    </div>
  );
};

export default BlogItem;
