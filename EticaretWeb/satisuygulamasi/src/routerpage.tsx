
import {
 BrowserRouter, Routes, Route
}  from 'react-router-dom';
import HomePage from "./HomePage";
import LoginPage from "./LoginPage";

function routerpage(){

    return <BrowserRouter>
        <Routes>
            <Route path='/' element={<HomePage />}/>
            <Route path='/login' element={<LoginPage />}/>
        </Routes>
    </BrowserRouter>
}

export default routerpage;