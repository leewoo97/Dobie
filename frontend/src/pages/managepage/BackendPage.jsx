import { useState, useEffect } from "react";
import NavTop from "../../components/common/NavTop";
import InnerContainer from "../../components/common/InnerContainer";
import Container from "../../components/common/Container";
import NavLeft from "../../components/common/NavLeft";
import BackendFrame from "../../components/manage/BackendFrame";

export default function BackendPage(){
    return(
        <>
            <NavTop/>
            <NavLeft num={3}/>
            <BackendFrame />
        </>
    );
}