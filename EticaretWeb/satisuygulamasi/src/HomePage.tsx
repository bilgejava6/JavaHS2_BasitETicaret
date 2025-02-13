import ProductComponent from "./component/ProductComponent";
import {useEffect, useState} from "react";
import SepetUrunComponent from "./component/SepetUrunComponent";
import './HomePage.css'

function HomePage(){
    const [urunList,setUrunList] = useState([]);
    const [sepetList,setSepetList] = useState([]);
    const getUrun = ()=>{
        fetch('http://localhost:9090/dev/v1/urun/get-all-urun').then(data=>data.json())
            .then(res=>setUrunList(res.data))
    }
    const getSepet = ()=>{
        const userId = localStorage.getItem('userId');
        fetch('http://localhost:9090/dev/v1/sepet/get-all-sepet/'+userId).then(data=>data.json())
            .then(res=> setSepetList(res.data))
    }
    useEffect(()=>{
            getUrun();
            getSepet();
        },[])

    const handleAdetDegistir = (urunId: number, yeniAdet: number) => {
        fetch('http://localhost:9090/dev/v1/sepet/up-down-sepet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'kullaniciId': localStorage.getItem('userId'),
                'urunId': urunId,
                'degisim': yeniAdet < 0 ? 'AZALT' : 'ARTTIR'
            })
        }).then(res => res.json())
            .then(res => {
                if(res.code === 200) {
                    getSepet();
                }
            });
    }

    const hesaplaSepetToplami = () => {
        return sepetList.reduce((toplam, urun: any) => toplam + urun.toplamFiyat, 0);
    }

    return(<div>
        <div className="container-fluid">
            <div className="row mt-5 text-center">
                <h1 className="mb-4">E-Ticaret Uygulaması</h1>
            </div>

            <div className="row">
                <div className="col-7">
                    <div className="row">
                        {
                            urunList.map((urun,index)=> {
                                return <div className="col-4 p-2" key={index}>
                                    <div className="product-card-wrapper">
                                        <ProductComponent urun={urun} onClick={()=>getSepet()} />
                                    </div>
                                </div>
                            })
                        }
                    </div>
                </div>
                <div className="col-5">
                    <div className="sepet-container p-3 rounded shadow-sm">
                        <h3 className='text-center mb-3'>Sepetim</h3>
                        <hr className='mt-2 mb-3'/>
                        <div className="sepet-items">
                            {
                                sepetList.map((urun, index) => {
                                    return <SepetUrunComponent 
                                        key={index} 
                                        urun={urun} 
                                        onAdetChange={handleAdetDegistir}
                                    />
                                })
                            }
                        </div>
                        <div className="sepet-toplam mt-3 pt-3 border-top">
                            <div className="d-flex justify-content-between align-items-center">
                                <h5 className="mb-0">Toplam</h5>
                                <span className="fs-5 fw-bold text-primary">
                                    {new Intl.NumberFormat('tr-TR', {
                                        style: 'currency',
                                        currency: 'TRY'
                                    }).format(hesaplaSepetToplami())}
                                </span>
                            </div>
                            <button className="btn btn-success w-100 mt-3">
                                Sepeti Onayla
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>)
}


export default HomePage;