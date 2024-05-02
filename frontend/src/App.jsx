import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Test from './pages/Test';

function App() {
  return (
    <div className="App">
      <h1>Initializing</h1>
      <BrowserRouter>
        <Routes>
          <Route path="/test" element={<Test />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
