import React, { useEffect, useRef } from "react";
import lottie from "lottie-web";

import animation from "../../assets/runloading.json";

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
      // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [animation]);
  
    return <div ref={animationContainer}></div>;
  }