package lk.ijse.memoclipsinlayered.bo;

import lk.ijse.memoclipsinlayered.bo.custom.impl.*;

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
                return new AdminBOImpl();
            case ALBUM_DETAIL:
                 return new AlbumDetailBOImpl();
            case ASSISTANT:
                 return new AssistantBOImpl();

            case BOOKING:
                 return new BookingBOImpl();

            case CUSTOMER:
                 return new CustomerBOImpl();

            case INVOICE:
                 return new InvoiceBOImpl();

            case ITEM:
                 return new ItemBOImpl();

            case PAYMENT:
                 return new PaymentBOImpl();

            case PHOTO_ALBUM:
                 return new PhotoAlbumBOImpl();

            case PHOTOGRAPHER:
                 return new PhotographerBOImpl();

            case PHOTO_SESSION:
                 return new PhotoSessionBOImpl();

            case PHOTO_STORAGE:
                 return new PhotoStorageBOImpl();

            case SUPPLIER:
                 return new SupplierBOImpl();

            case VIDEOGRAPHER:
                 return new VideographerBOImpl();

            case VIDEO_SESSION:
                 return new VideoSessionBOImpl();

            default:
                return null;
        }
    }
}
