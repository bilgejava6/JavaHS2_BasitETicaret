function SepetUrunComponent(props: {urun: any, onAdetChange: (urunId: number, yeniAdet: number) => void}){
    const urun = props.urun;
    
    const formatPrice = (price: number) => {
        return new Intl.NumberFormat('tr-TR', {
            style: 'currency',
            currency: 'TRY'
        }).format(price);
    }

    const handleAdetDegistir = (artis: number) => {
            props.onAdetChange(urun.urunId, artis);
    }

    return <div className="row m-2 p-2 border rounded bg-white align-items-center">
        <div className="col-2">
            <img style={{width:'35px', height:'35px', borderRadius:'35px', objectFit: 'cover'}} src={urun.urunResim} alt=""/>
        </div>
        <div className="col-4">
            <div className="text-truncate-2-lines" title={urun.urunAd}>
                {urun.urunAd}
            </div>
        </div>
        <div className="col-4 text-center d-flex align-items-center justify-content-center">
            <button className="btn btn-sm btn-light me-2" onClick={() => handleAdetDegistir(-1)}>
                <i className="fas fa-minus"></i>
            </button>
            <span className="mx-2">{urun.adet}</span>
            <button className="btn btn-sm btn-light ms-2" onClick={() => handleAdetDegistir(1)}>
                <i className="fas fa-plus"></i>
            </button>
        </div>
        <div className="col-2 text-end">
            <span className="text-primary fw-bold small">{formatPrice(urun.toplamFiyat)}</span>
        </div>
    </div>
}

export default SepetUrunComponent;