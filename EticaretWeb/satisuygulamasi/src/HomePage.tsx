import ProductComponent from "./component/ProductComponent";
import {useEffect, useState} from "react";

function HomePage(){
    const [urunList,setUrunList] = useState([]);
    useEffect(()=>{
            fetch('http://localhost:9090/dev/v1/urun/get-all-urun').then(data=>data.json())
                .then(res=>setUrunList(res.data))
        },[])
    return(<div>
        <div className="container">
            <div className="row mt-5 text-center">
                <h1>E Ticaret Uygulaması</h1>
            </div>

            <div className="row">
                <div className="col-9">
                    <div className="row">
                        {
                            urunList.map((urun,index)=> {
                                return <div className="col-4 p-2">
                                    <ProductComponent urun={urun}/>
                                </div>
                            })
                        }
                    </div>
                </div>
                <div className="col-3">
                    <h3 className='text-center'>S E P E T</h3>

                </div>
            </div>
        </div>

    </div>)
}


export default HomePage;