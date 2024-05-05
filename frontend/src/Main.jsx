import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import App from "./App";
import WelcomePage from "./pages/startpage/WelcomePage";
import LoginPage from "./pages/startpage/LoginPage";
import MainPage from "./pages/mainpage/MainPage";
import BackendPage from "./pages/managepage/BackendPage";
import FrontendPage from "./pages/managepage/FrontendPage";
import ProjectPage from "./pages/managepage/ProjectPage";
import DatabasePage from "./pages/managepage/BackendPage";
import RunPage from "./pages/managepage/RunPage";
import ErrorPage from "./pages/errorpage/ErrorPage";

function Main(){
    return(
        <Router>
            <Routes>
                <Route path="/" element={<App />}>
                    <Route  index element={<WelcomePage/>}/>
                    <Route path="login" element={<LoginPage/>}/>
                    <Route path="main">
                        <Route index element={<MainPage/>}/>
                        {/* 가이드 페이지 */}
                    </Route>
                    <Route path="manage">
                        <Route index element={<RunPage/>}/>
                        <Route path="backend" element={<ProjectPage/>}/>
                        <Route path="backend" element={<BackendPage/>}/>
                        <Route path="frontend" element={<FrontendPage/>}/>
                        <Route path="database" element={<DatabasePage/>}/>
                    </Route>
                </Route>
                <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </Router>
    );
}

export default Main;