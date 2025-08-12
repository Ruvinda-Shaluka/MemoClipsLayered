package lk.ijse.memoclipsinlayered.bo;


public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        ADMIN, ALBUM_DETAIL, ASSISTANT, BOOKING, CUSTOMER, INVOICE, ITEM,
        PAYMENT, PHOTO_ALBUM, PHOTOGRAPHER, PHOTO_SESSION, PHOTO_STORAGE,
        SUPPLIER, VIDEOGRAPHER, VIDEO_SESSION
    }

    public SuperBO getSuperBO(BOTypes boType) {
        switch (boType) {
            case ADMIN:
                //return new AdminBOImpl();
            case ALBUM_DETAIL:
                // return new AlbumDetailBOImpl();
                break;
            case ASSISTANT:
                // return new AssistantBOImpl();
                break;
            case BOOKING:
                // return new BookingBOImpl();
                break;
            case CUSTOMER:
                // return new CustomerBOImpl();
                break;
            case INVOICE:
                // return new InvoiceBOImpl();
                break;
            case ITEM:
                // return new ItemBOImpl();
                break;
            case PAYMENT:
                // return new PaymentBOImpl();
                break;
            case PHOTO_ALBUM:
                // return new PhotoAlbumBOImpl();
                break;
            case PHOTOGRAPHER:
                // return new PhotographerBOImpl();
                break;
            case PHOTO_SESSION:
                // return new PhotoSessionBOImpl();
                break;
            case PHOTO_STORAGE:
                // return new PhotoStorageBOImpl();
                break;
            case SUPPLIER:
                // return new SupplierBOImpl();
                break;
            case VIDEOGRAPHER:
                // return new VideographerBOImpl();
                break;
            case VIDEO_SESSION:
                // return new VideoSessionBOImpl();
                break;
            default:
                throw new IllegalArgumentException("Invalid BO type");
        }
        throw new UnsupportedOperationException("BO implementation not found for: " + boType);
    }
}
