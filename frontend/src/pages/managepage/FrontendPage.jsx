import { useState, useEffect } from "react";
import NavTop from "../../components/common/NavTop";
import Container from "../../components/common/Container";
import NavLeft from "../../components/common/NavLeft";
import FrontendFrame from "../../components/manage/FrontendFrame";

export default function FrontendPage(){
    return(
        <>
            <NavTop/>
            <NavLeft num={4}/>
            <FrontendFrame/>
        </>
    );
}