import { useState, useEffect } from "react";
import NavTop from "../../components/common/NavTop";
import InnerContainer from "../../components/common/InnerContainer";
import Container from "../../components/common/Container";

export default function DatabasePage(){
    return(
        <Container>
            <NavTop/>
            <InnerContainer/>
        </Container>
    );
}