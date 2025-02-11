
function ProductComponent(props: {urun:any}){
    const urun = props.urun;
    return(
        <div className="card shadow rounded-4" style={{width: '18rem'}}>
            <img height='250px' src={urun.resim} className="card-img-top" />
            <div className="card-body">
                <h5 className="card-title">{urun.kategoriAdi}</h5>
                <p className="card-text">{urun.ad}</p>
                <p className="card-text">{urun.fiyat} ₺</p>
                <a href="#" className="btn btn-primary">Sepete Ekle</a>
            </div>
        </div>
    )
}

export default ProductComponent;