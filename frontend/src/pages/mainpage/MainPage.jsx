import axios from "axios";
import { useState, useEffect } from "react";
import Container from "../../components/common/Container";
import NavTop from "../../components/common/NavTop"

export default function MainPage(){
    return(
        <Container>
            <NavTop/>
            <p>메인페이지</p>
        </Container>
    );
}
