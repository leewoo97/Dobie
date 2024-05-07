import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import App from "./App";
import WelcomePage from "./pages/startpage/WelcomePage";
import LoginPage from "./pages/startpage/LoginPage";
import MainPage from "./pages/mainpage/MainPage";
import BackendPage from "./pages/managepage/BackendPage";
import FrontendPage from "./pages/managepage/FrontendPage";
import ProjectPage from "./pages/managepage/ProjectPage";
import DatabasePage from "./pages/managepage/DatabasePage";
import RunPage from "./pages/managepage/RunPage";
import SginUpPage from "./pages/startpage/SginUpPage";
import ErrorPage from "./pages/errorpage/ErrorPage";

import CreateBackendPage from "./pages/createPage/CreateBackendPage";
import CreateDatabasePage from "./pages/createPage/CreateDatabasePage";
import CreateFrontendPage from "./pages/createPage/CreateFrontendPage";
import CreateProjectPage from "./pages/createPage/CreateProjectPage";
import GuidePage from "./pages/mainpage/GuidePage";

function Main() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<WelcomePage />} />
          <Route path="sginup" element={<SginUpPage />} />
          <Route path="login" element={<LoginPage />} />
          <Route path="main">
            <Route index element={<MainPage />} />
            <Route path="guide" element={<GuidePage />} />
          </Route>
          <Route path="manage">
            <Route index element={<RunPage />} />
            <Route path="project" element={<ProjectPage />} />
            <Route path="backend" element={<BackendPage />} />
            <Route path="frontend" element={<FrontendPage />} />
            <Route path="database" element={<DatabasePage />} />
          </Route>
          <Route path="create">
            <Route index element={<RunPage />} />
            <Route path="project" element={<CreateProjectPage />} />
            <Route path="backend" element={<CreateBackendPage />} />
            <Route path="frontend" element={<CreateFrontendPage />} />
            <Route path="database" element={<CreateDatabasePage />} />
          </Route>
        </Route>
        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </Router>
  );
}

export default Main;
