import swal from "sweetalert";

function ProductComponent(props: {urun:any, onClick: ()=>void}){
    const urun = props.urun;
    
    const formatPrice = (price: number) => {
        return new Intl.NumberFormat('tr-TR', {
            style: 'currency',
            currency: 'TRY',
            minimumFractionDigits: 2
        }).format(price);
    }

    const renderStars = (rating: number) => {
        return [...Array(5)].map((_, index) => (
            <span key={index} className={`star ${index < rating ? 'filled' : ''}`}>
                ★
            </span>
        ));
    }

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
                    props.onClick();
                }else {
                    swal('HATA', res.message, 'error')
                }
            })
    }

    return(
        <div className="product-card shadow rounded-3">
            {urun.taksit && (
                <div className="taksit-badge">
                    Kartsız {urun.taksit} taksit
                </div>
            )}
            <div className="product-image-container">
                <img src={urun.resim} className="product-image" alt={urun.ad} />
                <button className="favorite-button">
                    <i className="far fa-heart"></i>
                </button>
            </div>
            <div className="product-content">
                <div className="rating-container">
                    {renderStars(urun.rating || 4)}
                    {urun.reviewCount && (
                        <span className="review-count">({urun.reviewCount})</span>
                    )}
                </div>
                <h5 className="product-title text-truncate-2-lines" title={urun.ad}>
                    {urun.ad}
                </h5>
                <div className="price-container">
                    <span className="product-price">{formatPrice(urun.fiyat)}</span>
                </div>
                <button className="btn btn-primary w-100 mt-2" onClick={sepeteEkle}>
                    Sepete Ekle
                </button>
            </div>
        </div>
    )
}

export default ProductComponent;