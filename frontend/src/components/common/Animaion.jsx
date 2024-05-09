import React, { useEffect, useRef } from "react";
import lottie from "lottie-web";

import animation from "../../assets/loading.json";

export default function LottieAnimation() {
    const animationContainer = useRef(null);
  
    useEffect(() => {
      const anim = lottie.loadAnimation({
        container: animationContainer.current,
        renderer: "svg",
        loop: true,
        autoplay: true,
        animationData: animation,
      });
  
      return () => anim.destroy();
    }, [animation]);
  
    return <div ref={animationContainer}></div>;
  }