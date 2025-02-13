import {useState} from "react";
import swal from "sweetalert";
import {useNavigate} from "react-router";

function LoginPage(){
    const navigate = useNavigate();
    // Bunlar Değişkenlerimiz, özel olarak state leri takip edilir.
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    // Arrow function
    const doLogin = ()=>{
        fetch('http://localhost:9090/dev/v1/kullanici/login',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                'email': email,
                'password': password
            })
        }).then(res=> res.json())
            .then(res=> {
                if(res.code===200){ // giriş başarılı
                    localStorage.setItem('userId', res.data);
                    navigate('/');
                }else{
                    swal('DİKKAT!!!', res.message, 'error');
                }
            })
    }
    return <div className="container">
        <div className="row">
            <div className="col-3"></div>
            <div className="col-6">
                <div className="row">
                    <div className="mb-3 p-4 text-center">
                        <img style={{width: '150px', height: '150px', borderRadius: '150px'}}
                             src="https://cdn1.vectorstock.com/i/1000x1000/83/55/lock-screen-password-key-data-computer-security-vector-24138355.jpg"
                             alt=""/>
                    </div>
                    <div className="mb-3 mt-4">
                        <label className="form-label">Kullanıcı Adı</label>
                        <input type="text" className="form-control" onChange={evt=>setEmail(evt.target.value)}/>
                    </div>
                    <div className="mb-3 mt-4">
                        <label className="form-label">Şifre</label>
                        <input type="password" className="form-control" onChange={evt=>setPassword(evt.target.value)}/>
                    </div>
                    <div className="mb-3 d-grid">
                        <input type="button" className="btn btn-primary" value='Giriş Yap' onClick={doLogin}/>
                    </div>
                </div>
            </div>
            <div className="col-3"></div>
        </div>
    </div>
}

export default LoginPage;