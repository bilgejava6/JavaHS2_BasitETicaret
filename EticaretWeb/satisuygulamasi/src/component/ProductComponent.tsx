import swal from "sweetalert";
function ProductComponent(props: {urun:any}){
    const urun = props.urun;
    const sepeteEkle = ()=>{
        fetch('http://localhost:9090/dev/v1/sepet/add-to-sepet',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'kullaniciId': localStorage.getItem('userId'),
                'urunId': urun.id
            })
        }).then(res=> res.json())
            .then(res=> {
                if(res.code===200){
                    swal('Başarılı', 'Ürün sepete eklendi', 'success')
                }else {
                    swal('HATA', res.message, 'error')
                }
            })
    }
    return(
        <div className="card shadow rounded-4" style={{width: '18rem'}}>
            <img height='250px' src={urun.resim} className="card-img-top" />
            <div className="card-body">
                <h5 className="card-title">{urun.kategoriAdi}</h5>
                <p className="card-text">{urun.ad}</p>
                <p className="card-text">{urun.fiyat} ₺</p>
                <a href="#" className="btn btn-primary" onClick={sepeteEkle}>Sepete Ekle</a>
            </div>
        </div>
    )
}

export default ProductComponent;