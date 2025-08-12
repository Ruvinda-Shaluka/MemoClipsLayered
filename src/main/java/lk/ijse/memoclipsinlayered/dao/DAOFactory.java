package lk.ijse.memoclipsinlayered.dao;

import lk.ijse.memoclipsinlayered.dao.custom.impl.AdminDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        ADMIN,ALBUM_DETAIL,ASSISTANT,BOOKING,CUSTOMER,INVOICE,ITEM,PAYMENT,
        PHOTO_ALBUM,PHOTOGRAPHER,PHOTO_SESSION,PHOTO_STORAGE,SUPPLIER,VIDEOGRAPHER,VIDEO_SESSION;
    }

    public SuperDAO getSuperDAO(DAOTypes daoType) {
        switch (daoType) {
            case ADMIN:
                //return new AdminDAOImpl();
            case ALBUM_DETAIL:
                // return new AlbumDetailDAOImpl();
                break;
            case ASSISTANT:
                // return new AssistantDAOImpl();
                break;
            case BOOKING:
                // return new BookingDAOImpl();
                break;
            case CUSTOMER:
                // return new CustomerDAOImpl();
                break;
            case INVOICE:
                // return new InvoiceDAOImpl();
                break;
            case ITEM:
                // return new ItemDAOImpl();
                break;
            case PAYMENT:
                // return new PaymentDAOImpl();
                break;
            case PHOTO_ALBUM:
                // return new PhotoAlbumDAOImpl();
                break;
            case PHOTOGRAPHER:
                // return new PhotographerDAOImpl();
                break;
            case PHOTO_SESSION:
                // return new PhotoSessionDAOImpl();
                break;
            case PHOTO_STORAGE:
                // return new PhotoStorageDAOImpl();
                break;
            case SUPPLIER:
                // return new SupplierDAOImpl();
                break;
            case VIDEOGRAPHER:
                // return new VideographerDAOImpl();
                break;
            case VIDEO_SESSION:
                // return new VideoSessionDAOImpl();
                break;
            default:
                throw new IllegalArgumentException("Invalid DAO type");
        }
        throw new UnsupportedOperationException("DAO implementation not found for: " + daoType);
    }
}
