import { useState, useEffect } from "react";
import NavTop from "../../components/common/NavTop";
import InnerContainer from "../../components/common/InnerContainer";
import Container from "../../components/common/Container";
import NavLeft from "../../components/common/NavLeft";
import DatabaseFrame from "../../components/manage/DatabaseFrame";

export default function DatabasePage(){
    return(
        <>
            <NavTop/>
            <NavLeft num={5}/>
            <DatabaseFrame/>
        </>
    );
}